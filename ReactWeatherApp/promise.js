function addPromise (a,b){
    return new Promise(function(resolve,reject){
        if (typeof a === 'number' && typeof b === 'number') {
            resolve(a+b);
        }
        else {
            reject("not a number");
        }
    });

}

addPromise('a',6).then(function(numb){
    console.log(numb);
},function(err){
    console.log(err);

});