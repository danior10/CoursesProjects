const moongoose = require('mongoose');
const Schema = moongoose.Schema;

const reviewSchema  = new Schema({
    body: String,
    rating: Number,
    author:{
        type: Schema.Types.ObjectId,
        ref: 'User'
    }
});

module.exports = moongoose.model('Review', reviewSchema)