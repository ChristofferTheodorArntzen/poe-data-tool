import { get } from './BaseAdapter';

const devUrl = 'http://localhost:8080/health/';

export const checkHealthEndPoint = async () => {

    try {
        const response = await get(devUrl);

        if(response.status === 200) {
            return true;
        }
        
        return false;

    } catch (err) {
        console.log(err);
    }
} 