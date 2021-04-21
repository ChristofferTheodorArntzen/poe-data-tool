import React from 'react'
import { faqList } from './LandingPageTexts';

const FAQContainer = () => {

    return (
        <div id='faqContainer' className='container'>
            <div id='faqHeader' className='left-header'>
                <h2 className='title'>
                    Frequently Answered Questions
                </h2>
            </div>

            {faqList.map((FAQItem, i) => (
                <div id='faqItem' className='faq-item' key={'faq-item' + i}>
                    <div className='left-header'>
                        <h3 className='title'>{FAQItem.question}</h3>
                    </div>
                    <p className='content-text'>{FAQItem.answer}</p>
                    {(FAQItem.link != null) ? <a className='link content-text'>{FAQItem.linkText}</a> : null}
                </div>
            ))}
        </div>
    )
}

export default FAQContainer;