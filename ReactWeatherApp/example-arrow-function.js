



var name = ['ishu','manu','nancy','omkar'];
/*name.forEach(function(name){
    console.log('foreach call '+ name);

});

name.forEach((name) => {
    console.log('arrow function '+name);
    console.log('arrow');
});

name.forEach((name)=> 
console.log(name));

var returnMe = (name) => {
  return  name+'' 
};

console.log(returnMe('anuj'));*/

var person = {
    pname :'anuj',
    greet : function(){
        name.forEach((name) => {
            console.log(this.pname + name);
        });
    }
};

//person.greet();

var add =(a,b) => a+b;

var adds = (a,b) => {
    return a+b;
}

console.log(adds(5,6));