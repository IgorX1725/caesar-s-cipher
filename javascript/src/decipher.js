const alphabet =
new Array('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')


const setSalt = (letter, salt) => {

    let alphabetIndexLetter = alphabet.indexOf(letter)

    if(alphabetIndexLetter != -1 && (alphabetIndexLetter - salt) >= 0) {

        return  alphabet[alphabetIndexLetter - salt]

    }else if(alphabetIndexLetter == -1){

        return letter
    }else {
        
        let index =  alphabet.length-1 - (salt-1 - alphabetIndexLetter)
        return  alphabet[index]
    }
}
module.exports = { 

    decipher : (json) =>{
        const cipher = json.cifrado
        const salt = json.numero_casas
        console.log('cipher: '+ cipher)
        let deciphered = ''
        cipher.toLowerCase()
        const cipherSize = cipher.length
        for(i = 0; i < cipherSize; i++) {
            let letter = cipher.charAt(i)
            deciphered += setSalt(letter,salt)
            
        }
        console.log('Deciphered text: '+deciphered)
        json.decifrado = deciphered
        return json
    }
}