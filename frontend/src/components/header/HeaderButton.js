/* eslint-disable react/prop-types */
import React, { useState, useEffect } from 'react'
import Button from "@material-ui/core/Button";
import { Link } from "react-router-dom";



const HeaderButton = ({ variant, size, startIcon, directoryLink, buttonText, reSizeWidth }) => {

    const [hideText, setHideText] = useState(window.innerWidth > reSizeWidth);

    const updateMedia = () => {
        setHideText(window.innerWidth > reSizeWidth);
    }

    useEffect(() => {
        window.addEventListener('resize', updateMedia);
        return () => window.removeEventListener('resize', updateMedia);
    })

    return (
        <div className='header-content-container'>
            <Button
                variant={variant}
                size={size}
                startIcon={startIcon}
                component={Link}
                to={directoryLink}
            >
                {(hideText) ? buttonText : null}
            </Button>
        </div>
    )
}

export default HeaderButton;