const sha1 = require('sha1')
module.exports = {
    getSHA1ofJSON : (json)=>{
        const hash = sha1(json.decifrado)
        console.log('result hash: '+hash)
        json.resumo_criptografico = hash
        return json
    }   
}   