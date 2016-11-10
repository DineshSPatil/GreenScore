var axios = require('axios');

const OPEN_WEATHER_MAP_URL = 'http://api.openweathermap.org/data/2.5/weather?&appid=5823e9e2ea703a25fd4ef33f5f2cff8d&units=imperial';
//5823e9e2ea703a25fd4ef33f5f2cff8d
//http://api.openweathermap.org/data/2.5/weather?q=pune&appid=5823e9e2ea703a25fd4ef33f5f2cff8d
module.exports = {
    getTemp:function(location){
        var encodeLocation = encodeURIComponent(location);
    var requestUrl = `${OPEN_WEATHER_MAP_URL}&q=${encodeLocation}`;
        return axios.get(requestUrl).then(function(response){
            if(response.data.cod && response.data.message) {
                throw new Error(error.data.message);
            }
            else {
                console.log(response.data.main.temp);
                return response.data.main.temp;
            }

        },function(error){
            throw new Error(error.data.message);

        })

    }
}