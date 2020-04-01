const fs = require('fs')
const dir = "answer.json"

module.exports = {

    save(json){
        jsonString = JSON.stringify(json)
         fs.writeFile(dir, jsonString, 'utf8', function (err) {
            if (err) {
                console.log("An error occured while writing JSON Object to File.");
                return console.log(err);
            }
            console.log(`JSON file has been saved: ${dir}`)
        })
        return this.getJson()
    },

    update(json){
        const jsonString = JSON.stringify(json)
        fs.writeFile(dir, jsonString, 'utf8', function (err) {
            if (err) {
                console.log("An error occured while writing JSON Object to File.");
                return console.log(err);
            }
        
            console.log(`JSON file has been updated: ${dir}`);
        })
        return  fs.createReadStream('./answer.json')
    },

    getJson(){
        return new Promise((resolve, reject)=>{
            fs.readFile( dir, 'utf8', function(err, contents) {
                if(err){
                    reject(err)
                }
                resolve(JSON.parse(contents))
            });
        }) 
    }
}
