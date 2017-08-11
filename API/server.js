
var express = require('express'),
  app = express(),
  port = 8080,
  mongoose = require('mongoose'),
  Event = require('./api/models/model'),
  bodyParser = require('body-parser');

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost/RadioDoor');

app.use(bodyParser.urlencoded({extended:true}));
app.use(bodyParser.json());

var routes = require('./api/routes/routes');
routes(app);


app.listen(port);

console.log('todo list RESTful API server started on: ' + port);
