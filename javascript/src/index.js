const requests = require('./requests')
const jsonFile = require('./jsonFile')
const decipher = require('./decipher')
const sha1 = require('./sha1')

requests.getJson()
.then( json => jsonFile.save(json))
.then(json =>{
    const deciphered = decipher.decipher(json.cifrado, json.numero_casas)
    json.decifrado = deciphered
    return json })
.then(json=>{
    const hash = sha1.getSHA1ofJSON(json.decifrado)
    json.resumo_criptografico = hash
    return json})
.then( json =>{
    jsonFile.update(json)
    requests.postJson()})

