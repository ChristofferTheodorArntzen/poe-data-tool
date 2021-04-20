/* eslint-disable react/prop-types */
import React, { useState, useEffect } from 'react'

const HeaderContent = ({ text, label, reSizeWidth }) => {

    const [hideContent, setHideContent] = useState(window.innerWidth < reSizeWidth);

    const updateMedia = () => {
        setHideContent(window.innerWidth < reSizeWidth);
    }

    useEffect(() => {
        window.addEventListener('resize', updateMedia);
        return () => window.removeEventListener('resize', updateMedia);
    })

    if (hideContent) return null;

    return (
        <div className='header-content-container'>
            <label className='header-label'>
                {label}
            </label>
            <span className='header-span'>
                {text}
            </span>
        </div>
    )

}

export default HeaderContent;