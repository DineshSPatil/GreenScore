"use strict";

var React = require('react');
var ReactDOM = require('react-dom');
var {Route, Router, IndexRoute, hashHistory} = require('react-router');
var Main = require('Main');
var Weather = require('Weather');
var About = require('About');
var Examples = require('Examples');
//var $ = require("jquery")

//load foundtion
require('style!css!foundation-sites/dist/foundation.min.css');
jQuery(document).foundation();

ReactDOM.render(
    <Router history={hashHistory}>
        <Route path="/" component={Main}>
        <Route path="about" component={About}/>
        <Route path="example" component={Examples}/>
            <IndexRoute component={Weather} /> 
        </Route>
    </Router>,
    document.getElementById("app")
);