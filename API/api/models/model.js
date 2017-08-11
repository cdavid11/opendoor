'use strict';

var mongoose = require ('mongoose');
var Schema = mongoose.Schema;

var EventSchema = new Schema({
	name: {
		type: String,
	},
	time: {
		type: Date,
		default: Date.now
	}
})

module.exports = mongoose.model('Events', EventSchema);
