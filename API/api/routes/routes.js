'use strict';
module.exports = function(app) {

	var radiodoor = require('../controllers/controller');
	
	app.route('/events').get(radiodoor.list_all_events)
			.post(radiodoor.create_event);

	app.route('/events/:eventID').get(radiodoor.read_event);

};
