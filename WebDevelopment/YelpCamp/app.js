const mongoose = require('mongoose');
const express = require('express');
const path = require('path');
const ejsMate = require('ejs-mate');
const session = require('express-session');
const Campground = require('./models/campground');
const app = express();
const methodOverride = require('method-override');
const catchAsync = require('./utils/catchAsync');
const ExpressError = require('./utils/ExpressError');
const Joi = require('joi');
const {campgroundSchema, reviewSchema} = require('./schemas'); 
const Review = require('./models/review');
const flash = require('connect-flash');
const User = require('./models/user');
const passport = require('passport');
const LocalStrategy = require('passport-local')
const usersRoutes = require('./routes/users')
const {isLoggedIn} = require('./middleware')
const campgrounds = require('./Controllers/campgrounds')


mongoose.connect('mongodb://localhost:27017/yelp-camp', {useNewUrlParser:true, useCreateIndex:true, useUnifiedTopology:true});
const db = mongoose.connection;
db.on('error', console.error.bind(console, "connection error:"));
db.once('open', () => {
    console.log('DATABASE CONNECTED');
})

app.engine('ejs', ejsMate);
app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));
app.use(express.urlencoded({ extended: true }));
app.use(methodOverride('_method'));
app.use(flash());


const sessionConfig = {
    secret:'thisshouldbeabettersecret!',
    resave: false,
    saveUninitialized: true,
    cookie: {
        httpOnly:true,
        expires: Date.now() + 1000*60*60*24*7,
        maxAge: 1000*60*60*24*7
    }
}
app.use(session(sessionConfig))
app.use(passport.initialize());
app.use(passport.session());
passport.use(new LocalStrategy(User.authenticate()));

passport.serializeUser(User.serializeUser());
passport.deserializeUser(User.deserializeUser());

const validateCampground = (req, res, next) => {
    const {error} = campgroundSchema.validate(req.body);
    if(error){
        const msg = error.details.map(el => el.message).join(',')
        throw new ExpressError(msg, 400)
    }else{
        next();
    }
}

const validateReview = (req, res, next) => {
    const {error} = reviewSchema.validate(req.body);
    if(error){
        const msg = error.details.map(el => el.message).join(',')
        throw new ExpressError(msg, 400)
    }else{
        next();
    }
}


app.use((req, res, next) =>{
    res.locals.currentUser = req.user;
    res.locals.success = req.flash('success');
    res.locals.error = req.flash('error');
    next();
})

app.use('/', usersRoutes)

app.get('/', (req,res) => {
    res.render('home')
})

app.get('/campgrounds', catchAsync(campgrounds.index));

// app.get('/campgrounds', catchAsync(async (req, res) => {
//     const campgrounds = await Campground.find({});
//     res.render('campgrounds/index', {campgrounds})
// }))

app.post('/campgrounds', isLoggedIn, validateCampground, catchAsync(async (req, res, next) => {
    // if(!req.body.campground) throw new ExpressError('Invalid Campground Data', 400)
    const campground = req.body.campground;
    const newCamp = new Campground(campground)
    await newCamp.save();
    req.flash('success', 'Successfully made a new campground!')
    res.redirect(`/campgrounds/${campground._id}`)

}))

app.get('/campgrounds/new', isLoggedIn, (req, res) => {
    res.render('campgrounds/create')
})

app.delete('/campgrounds/:id', isLoggedIn, catchAsync(async (req, res) => {
    const {id} = req.params;
    const campground = await Campground.findByIdAndDelete(id)
    res.redirect('/campgrounds')
}))

app.put('/campgrounds/:id', isLoggedIn, catchAsync(async(req, res) => {
    const campground = req.body.campground;
    console.log(campground);
    const {id} = req.params;
    const updatedCampground = await Campground.findByIdAndUpdate(id, campground, {new:true});
    req.flash('success', 'Successfully update a campground!')
    res.redirect(`/campgrounds/${updatedCampground._id}`)
}))

app.get('/campgrounds/:id/edit', isLoggedIn, catchAsync(async (req, res) => {
    const {id} = req.params;
    const campground = await Campground.findById(id);
    res.render('campgrounds/edit', {campground});
}))

app.get('/campgrounds/:id', isLoggedIn, catchAsync(async (req, res) => {
    const {id} = req.params;
    const campground = await Campground.findById(id).populate('reviews');
    if(!campground){
        req.flash('error', "Cannot find that campground!");
        res.redirect('/campgrounds')
    }else{
        res.render('campgrounds/show', {campground});
    }
}))

app.get('/makecampground', catchAsync(async (req, res) => {
    const camp = new Campground( { title: 'My backyard', description:'chip camping!'});
    await camp.save();
    res.send(camp)
}))

app.post('/campgrounds/:id/reviews', isLoggedIn, validateReview, catchAsync(async (req, res) => {
    const campground = await Campground.findById(req.params.id);
    const review = new Review(req.body.review);
    campground.reviews.push(review);
    await review.save();
    await campground.save();
    req.flash('success', 'Successfully made a new review!')
    res.redirect(`/campgrounds/${campground._id}`);
}))

app.delete('/campgrounds/:id/reviews/:reviewId', isLoggedIn, catchAsync(async (req, res, next) =>{
    const {id, reviewId} = req.params;
    await Campground.findByIdAndUpdate(id, {$pull: {reviews: reviewId}});
    await Review.findByIdAndDelete(req.params.reviewId);
    req.flash('success', 'Successfully deleted a review')
    res.redirect(`/campgrounds/${id}`);
}))

app.all('*', (req, res, next) =>{
    next(new ExpressError('Page Not Found', 404))
})

app.use((err, req, res, next) => {
    const {statusCode = 500} = err
    if(!err.message) err.message = "Oh No Something Went Wrong"
    res.status(statusCode).render('error', {err})
})

app.listen(3000, () => {
    console.log('Serving on port 3000')
})
