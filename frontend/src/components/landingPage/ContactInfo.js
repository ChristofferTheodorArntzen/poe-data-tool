import React from 'react'

const contactUrl = 'https://github.com/ChristofferTheodorArntzen/poe-data-tool';

const ContactInfo = () => {


    return (
        <div id='ContactInfoContainer' className='container'>
            <div id='ContactInfoHeader' className='left-header'>
                <h2 className='title'>
                    Contact Info
                </h2>
            </div>
            <p className='content-text'>You can reach the creator over at <a className='link' href={contactUrl} target='_blank' rel='noreferrer'>github</a>.</p>
        </div>
    )
}

export default ContactInfo;