import React from 'react'
import Emoji from '../Emoji';
import { welcomeFullDesc } from './LandingPageTexts';

const MainContainer = () => {

    return (
        <div id='welcomeContainer' className='container'>
        <div id='welcomeHeader' className='center-header'>
            <h1 className='title'>Welcome to The Lazy Exile!</h1>
            <Emoji symbol='🦥' label='sloth' />
            <Emoji symbol='💤' label='z-z-z' />
        </div>

        <h5 className='gutter'> An automatic price checker.
        This application connects to your
        <a className='link' href='https://www.pathofexile.com' target='_blank' rel='noreferrer'> PoE account </a>
        and finds an estimated price for item in preselected stash tabs!
    </h5>
        <p className='content-text'>
            {welcomeFullDesc}
        </p>
    </div> 
    )
}

export default MainContainer;