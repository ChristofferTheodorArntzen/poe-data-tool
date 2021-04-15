import { post } from './BaseAdapter';

const devUrl = 'http://localhost:8080/user/';

export async function postUserInfo(data) {

    const { accountName, league, realm, sessionId } = data;

    const response = await post(devUrl, { accountName, league, realm, sessionId })

    return response;
}


