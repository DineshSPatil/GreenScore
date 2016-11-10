var React = require('react');

/*var About = React.createClass({
    render: function(){
        return(
            <h1>About compnent</h1>
        );
    }
});*/

//arrow function use - if component use only render method we can use it

var About = (props) => {
    return (
        <div>
            <h1 className="text-center">About</h1>
            <p> this is about page </p>
            <ul>
                <li><a href="https://facebook.github.io/react/"> React</a> this is about react</li>
            </ul>
        </div>
    );
};

module.exports = About;