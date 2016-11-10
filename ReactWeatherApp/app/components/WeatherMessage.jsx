var React = require('react');

/*var WeatherMessage = React.createClass({
    render: function(){
        var location = this.props.location;
        var temp = this.props.temp;
        return(
            <h1>its {temp} in {location}</h1>
        );
    }
});*/

//use of arrow function

var WeatherMessage = (props) => {
    var {location,temp} = props;
            return(
            <h1>its {temp} in {location}</h1>
        );
}

module.exports = WeatherMessage;