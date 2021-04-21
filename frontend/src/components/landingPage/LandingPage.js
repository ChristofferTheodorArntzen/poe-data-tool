/* eslint-disable no-unused-vars */
import React from 'react'
import '../../styles/LandingPage.css';
import WelcomeContainer from './WelcomeContainer';
import HowToUseContainer from './HowToUseContainer';
import FAQContainer from './FAQContainer';
import PatchNotesContainer from './PatchNotesContainer';
import ContactInfo from './ContactInfo';

const LandingPage = () => {

    return (
        <div className='landing-page-container'>
            <main className='main-container'>
                <WelcomeContainer />
                <HowToUseContainer />
                <FAQContainer />
                <PatchNotesContainer />
                <ContactInfo />
            </main>
        </div>
    )

}

export default LandingPage;