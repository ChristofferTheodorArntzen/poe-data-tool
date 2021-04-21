

const localStorageKey = 'poe-data-tool'
const suffix = '-user'

export function saveUserToLocalStorage(data) {
    localStorage.setItem(localStorageKey + suffix, JSON.stringify(data));
}

export function getUserFromLocalStorage() {

    const userData = localStorage.getItem(localStorageKey + suffix);

    if (userData) {
        const dataObject = JSON.parse(userData);
        return dataObject;
    }

    return null;
}

export function deleteUserFromLocalStorage() {
    localStorage.removeItem(localStorageKey + suffix);
}