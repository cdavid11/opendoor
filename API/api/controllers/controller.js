'use strict';

var mongoose = require('mongoose'),
	Event = mongoose.model('Events');

exports.list_all_events = function(req,res) {

	Event.find({}, function(err, task) {
		if (err) res.send(err);
	res.json(task);
	});
};

exports.create_event = function(req,res){
	var new_event = new Event(req.body);
	new_event.save(function(err, task) {
		if(err) res.send(err);
	res.json(task);
	});
};

exports.read_event = function(req,res){
	Event.findById(req.params.EventId, function(err, task){
		if(err) res.send(err);
	res.json(task);
	});
};


