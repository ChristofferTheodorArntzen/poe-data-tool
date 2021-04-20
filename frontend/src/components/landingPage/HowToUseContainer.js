import React from 'react'
import { howToDescEnd, howToDescLogin, howToDescSessionId, howToDescSubscription } from './LandingPageTexts';

const HowToUseContainer = () => {

    return (
        <div id='howToUseContainer' className='container'>
        <div id='howToUseHeader' className='left-header'>
            <h2 className='title'>{'How To Use \'The Lazy Exile\''}</h2>
        </div>
        <ol className='content-text'>
            <li>
                {howToDescSessionId}
            </li>
            <li>
                {howToDescLogin}
            </li>
            <li>
                {howToDescSubscription}
            </li>
        </ol>
        <p className='content-text'>{howToDescEnd}</p>
    </div>
    )
}

export default HowToUseContainer;