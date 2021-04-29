/* eslint-disable no-unused-vars */
import React, { useState, useEffect, useRef } from 'react';
import PropTypes from 'prop-types';
import ToolTipBox from './ToolTipBox';

const MAGIC_ITEM = 1;
const RARE_ITEM = 2;
const UNIQUE_ITEM = 3;

const blueHexColor = '#0073e6';
const rareHexColor = '#eee72b';
const uniqueHexColor = '#ffbf00';
const defaultColor = '#e6e6e6';

const Tooltip = ({ component, item }) => {

    const [isShown, setIsShown] = useState(false);
    const [yPos, setYPos] = useState(null);
    const [xPos, setXPos] = useState(null);
    const [primaryColor, setPrimaryColor] = useState(null);

    const handleOnMouseEnter = (event) => {
        // TODO: play with some numbers or find out a better way to determine the yPos and xPos for the tooltip. This works for now as a WIP
        let yPos = event.screenY - (100);
        setYPos(yPos);
        setXPos(event.screenX);
        setIsShown(true);
    }

    const handleOnMouseLeave = () => {
        setIsShown(false);
        setYPos(null);
        setXPos(null);
    }

    const determinePrimaryColor = (item) => {

        console.log('determining primary color !!!');

        switch (item.frameType) {   
            case MAGIC_ITEM: return blueHexColor;
            case RARE_ITEM: return rareHexColor;
            case UNIQUE_ITEM: return uniqueHexColor;
            default: return defaultColor;
        }
    }

    useEffect( () => {
        setPrimaryColor(determinePrimaryColor(item));
    }, [])

    return (
        <>
            <div className='toolTipImage'
                onMouseEnter={(e) => handleOnMouseEnter(e)}
                onMouseLeave={handleOnMouseLeave}
            >
                {component}
            </div>
            <ToolTipBox item={item} yPos={yPos} xPos={xPos} primaryColor={primaryColor}/>
            {/* { isShown 
                ? < ToolTipBox 
                    item={item} 
                    yPos={yPos} 
                    xPos={xPos} 
                    primaryColor={primaryColor} 
                /> 
                : null}
                 TODO: uncomment and remove the line above before commit*/}

        </>
    )
}

Tooltip.propTypes = {
    component: PropTypes.element,
    item: PropTypes.object,
}

export default Tooltip;