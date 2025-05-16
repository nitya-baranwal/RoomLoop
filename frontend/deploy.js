const { deploy } = require('vercel');

async function main() {
    try {
        await deploy({
            token: process.env.VERCEL_TOKEN,
            env: {
                VERCEL_TOKEN: process.env.VERCEL_TOKEN
            }
        });
        console.log('Deployment successful!');
    } catch (error) {
        console.error('Deployment failed:', error);
    }
}

main();
