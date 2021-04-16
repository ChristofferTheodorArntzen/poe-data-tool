import { deleteRequest, get } from './BaseAdapter'

const devUrl = 'http://localhost:8080/subscription/';

export async function getSubscriptions() {
    try {
        const response = await get(devUrl);
        console.log(response);

        if(response.status === 200) return response.data;
        return null;
    } catch(err) {
        console.log(err);
    }

    return null;
}

export async function deleteSubscription(data) {
    
    try {
        const response = await deleteRequest(devUrl, data);
        return response;
    } catch (err) {
        console.log(err);
    }
    
    return null;
}