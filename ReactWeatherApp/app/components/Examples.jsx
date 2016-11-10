var React = require('react');
var {Link} = require('react-router');

var Examples = React.createClass({
    render: function(){
        return(
            <div>
                <h1 className="text-center">Examples</h1>
                <p>lets try few examples</p>
                <ol>
                    <li>
                        <Link to="/?location=pune">pune</Link>
                    </li>
                    <li>
                        <Link to="/?location=mumbai">mumbai</Link>
                    </li>
                </ol>
            </div>
            
        );
    }
});

module.exports = Examples;