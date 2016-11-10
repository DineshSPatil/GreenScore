var React = require('react');
var {Link} = require('react-router');

var Nav = React.createClass({
    onSearch:function(e) {
        e.preventDefault();
        alert("hello");
    },
    render: function(){
        return(           
            <div className="top-bar">
                <div className="top-bar-left">
                    <ul className="menu">
                        <li className="menu-text">Weather App</li>
                        <li><Link to="/"> Get Weather</Link></li>
                        <li><Link to="/about"> About</Link></li>
                        <li><Link to="/example"> Examples</Link></li>
                    </ul>
                </div>
                <div className="top-bar-right">
                    <form onSubmit={this.onSearch}>
                        <ul className="menu">
                            <li> <input type="search" placeholder="Search" /> </li>
                            <li><button type="submit" className="button">Search</button></li>
                        </ul>

                    </form>
                </div>
            </div>
        );
    }
});

module.exports = Nav;