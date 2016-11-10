var React = require('react');
var WeatherForm = require('WeatherForm');
var WeatherMessage = require('WeatherMessage');
var openWeatherMap = require('openWeatherMap');

var Weather = React.createClass({
    getInitialState: function () {
        return {
            isLoading: false
        }

    },
    handleSearch: function (location) {
        
        var that = this;
        that.setState({ isLoading: true })
        openWeatherMap.getTemp(location).then(function (temp) {
            that.setState({
                location: location,
                temp: temp,
                isLoading: false
            });

        }, function (err) {
            that.setState({ isLoading: false })
            alert(err);

        })

    },
    render: function () {
        var {isLoading, location, temp} = this.state;
        function renderMessage() {
            if (isLoading) {
                return <h2>fetching data...</h2>
            }
            else if (location && temp) {
                return <WeatherMessage temp={temp} location={location} />
            }
        }
        return (
            <div>
                <h1> weather component</h1>
                <WeatherForm onSearch={this.handleSearch} />
                {renderMessage()}
            </div>
        );
    }
});

module.exports = Weather;