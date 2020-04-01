const requests = require('./requests')
const jsonFile = require('./jsonFile')
const decipher = require('./decipher')
const sha1 = require('./sha1')


const get = requests.get()

get.then(response => {
    return jsonFile.save(response.data)
})
.then(json => {
    return decipher.decipher(json)
})
.then(json =>{
    return sha1.getSHA1ofJSON(json)
})
.then(json =>{
    return jsonFile.update(json)
})
.then(json =>{
    return requests.post(json)
})
.then(response =>{
    console.log(response)
}).catch(error => {
    console.log('An error occurred in the process: '+ error)
})