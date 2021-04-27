import { deleteRequest, get, put, post } from './BaseAdapter'

const devUrl = 'http://localhost:8080/subscription/';

export async function getSubscriptions() {
    try {
        const response = await get(devUrl);
        if (response.status == 200) return response.data;
        return null;
    } catch (err) {
        console.log(err);
    }

    return null;
}

export async function deleteSubscription(data) {

    try {
        const response = await deleteRequest(devUrl + data.pk, data);
        
        if ( response.status == 200) {
            return true;
        }
       
        return false;
    } catch (err) {
        console.log(err);
    }

    return null;
}

export async function submitSubscription(data) {

    let subscription = {
        pk: data.pk,
        name: data.name,
        tabIds: [data.tabIds],
        currencyThreshold: data.currencyThreshold,
        currencyType: data.currencyType,
        itemTypes: data.itemTypes,
        isActive: false,
    }

    try {
        let response;

        if (subscription.pk == null || subscription.pk.length == 0) {
            response = await post(devUrl, subscription);
        } else {
            response = await put(devUrl + subscription.pk, subscription);
        }

        if (response.status == 200 || response.status == 201) {
            return response.data;
        }

        return null;
    } catch (err) {
        console.log(err);
    }

    return null;
}

export async function setSubscriptionActive(subscription) {

    try {

        subscription.active = !subscription.active;
        subscription.isActive = !subscription.isActive;

        const response = await put(devUrl + subscription.pk, subscription);

        if (response.status == 200 || response.status == 201) {
            return response.data;
        }

        return null;

    } catch (err) {
        console.log(err);
    }

}