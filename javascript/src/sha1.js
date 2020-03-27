const sha1 = require('sha1')
module.exports = {
        getSHA1ofJSON : (deciphered)=>{
            const hash = sha1(deciphered)
            console.log('result hash: '+hash)
            return hash
        }   
    }   