const FormData = require('form-data')
const https = require('https');
const axios = require('axios');
const authentication = require('./authentication')
const fs = require('fs')


const urlToGetJson = `https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=${authentication.token}`

    module.exports ={
        getJson(){
            console.log("Getting json...")
             return  axios.get(urlToGetJson).then(res =>{
                return res.data
            }).catch(function (error) {
               console.log(`an error occurred while getting the json: ${error}`);
           })

            },
        postJson(){
            const form = new FormData();
            const readStream = fs.createReadStream('./answer.json')
            form.append('answer', readStream);

            const req = https.request({
                  host: 'api.codenation.dev',
                  port: '443',
                  path: `/v1/challenge/dev-ps/submit-solution?token=${authentication.token}`,
                  method: 'POST',
                  headers: form.getHeaders(),
                },
                response => {
                  console.log("StatusCode: "+response.statusCode);
                  response.on('data', (d) => {
                    process.stdout.write(d);
                  });
                });
                form.pipe(req)
                req.on('error', (e) => {
                    console.error(e);
                  });
        }
    }