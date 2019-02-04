const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

const CODE_OK = 200;
const CODE_ERROR = 404;

exports.user = functions.https.onRequest((req, res) => {

    if(req.method!="GET")
        return res.status(CODE_ERROR).send(`Method must be GET`);
    
    var id = req.query.id;
    if (!id) {
        return res.status(CODE_ERROR).send(`Need user id`);
    }
    var userRef = getUserRef(id);
    return userRef.once('value', (snapshot) => {
        return response(res,snapshot,CODE_OK);
    });
});

exports.updateuser = functions.https.onRequest((req, res) => {

    var id = req.query.id;
    if (!id) {
        return res.status(CODE_ERROR).send(`Need user id`);
    }

    var userData = req.body;
    var userRef = getUserRef(id);
    return userRef.once("value")
                                .then(snapshot =>{
                                    if(snapshot.exists()){
                                         return update(userRef,userData)
                                         .then(code => response(res,snapshot.val(),code))
                                    }
                                    else{
                                        return response(res,{"status":"error"},CODE_ERROR);
                                    }

                                });

});

function getUserRef(id){
    return admin.database().ref('users/' + id);
}

function update(userRef, data) {
    return userRef.set(data, error => { return Promise.resolve(error) }).then(()=>{return Promise.resolve(CODE_OK)});
 }
function response(res, items, code) {
    return Promise.resolve(res.status(code)
        .type('application/json')
        .send(items))
}


///////////////////////////////Attributes//////////////////////////////////////////
exports.attributes = functions.https.onRequest((req, res) => {
    if(req.method!="GET")
        return res.status(CODE_ERROR).send(`Method must be GET`);
    
    var dataRef = getAttributesRef();
    return dataRef.once('value', (snapshot) => {return responseJSON(res, snapshot.val(), CODE_OK)});
});

function getAttributesRef(){
    return admin.database().ref('single_choice_attributes');
}
///////////////////////////////Cities//////////////////////////////////////////
exports.cities = functions.https.onRequest((req, res) => {
    if(req.method!="GET")
        return res.status(CODE_ERROR).send(`Method must be GET`);
    
    var dataRef = getCitiesRef();
    return dataRef.once('value', (snapshot) => {return responseJSON(res, snapshot.val(), CODE_OK)});
});

function getCitiesRef(){
    return admin.database().ref('cities');
}

/////////////////////////////////////////////////////////////////////////////
function responseJSON(res, item, code){
    return Promise.resolve(res.json(item))
}

 

