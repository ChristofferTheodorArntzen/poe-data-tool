

export const welcomeFullDesc = 'There are automated background processes which checks your stash intermittently and evaluates the mods on the items that you stash. It uses other peoples listings to evaluate the value of all the items and presents them to you on in the Feed. For more info on how to set up this application for your account scroll down to the \'How To\' section.';
export const howToDescSessionId = 'To start with you need your session ID from the Path of exile web site. To get the session ID from the web site hit \'F12\' on your keyboard, or right click and select \'inspect\'. A new window will appear with the title \'DevTools\' in the menu at the top click on the \'Application\' tab. Here you should see a menu at the left hand of the window. Select the Cookies menu item and copy the POESESSID value to your clipboard, you will need this later. (Note: copy-paste to notepad or something similar)';
export const howToDescLogin = 'Head over to login page for this application and enter in you account name, league, realm (pc, console etc.) and PoE session ID. You will be logged in to the application here and it will notify you if you entered in the credentials correct. Note: if for some reason the application stops working check that the session id is correct, it is updated intermittently and can change, simply log out and in again with the new session ID to fix this.';
export const howToDescSubscription = 'After you have logged in head over to the subscription tab and set up a subscription to be used. Choose a descriptive name, the threshold for notifying you, the currency type you want the price to be displayed as, the item types you want to search for and the tabs you want the application to listen to (note: this takes the order from left to right in your stash in-game, so if you reorder the selected tabs you need to update the subscription).';
export const howToDescEnd = 'That\'s it, you\'ve set up the application. Head over to \'FEED\' to see the items.';


export const faqList = [
    {
        question: 'When i log in to this app, i get the message \'Could not connect to Path of Exile...\' what does that mean?',
        answer: 'This means that the login credentials are not entered correctly, please make sure you are using the right account name, realm, league and session id.',
        link: null
    }, 
    {
        question: 'My items are not selling, even when i try to sell it at the estimated price.',
        answer: 'The estimation is only done once for each item in you stash, and the estimation is just an estimation. The price can vary heavily on rare gear due to the nature of how items work in PoE, try lowering the price or look up similar items on the trade-site.',
        link: null
    }, 
    {
        question: 'My subscription is not filtering out all unwanted items!',
        answer: 'This application is still in a early alpha state so some kinks are still to be expected. Please double check that the subscription is including the right items and that the threshold is within the bounds of the estimated price. If everything is correct please head over to the github page and report the issue.',
        link: 'https://github.com/ChristofferTheodorArntzen/poe-data-tool',
        linkText: 'Github'       
    }
]
export const patchNotes = [
    {
        patchVersion: '0.001',
        patchNoteDetail: 'Add front page. Fixed css for all pages. Fixed scroll in Feed component.'
    },
    {
        patchVersion: '0.000',
        patchNoteDetail: 'Created this project, made a backend and frontend, setup of project.'
    }
]