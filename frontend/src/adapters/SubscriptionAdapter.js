import { deleteRequest, get } from './BaseAdapter'

const devUrl = 'http://localhost:8080/subscription/';

export async function getSubscriptions() {

    try {
        const response = get(devUrl);

        return response;
    } catch(err) {
        console.log(err);
    }

}

export async function deleteSubscription(data) {
    return deleteRequest(devUrl, data)
    .then( resp => {
        console.log(resp);
    })
    .catch(err => console.log(err))

}