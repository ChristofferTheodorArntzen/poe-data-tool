/* eslint-disable no-unused-vars */
import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import ToolTipBox from './ToolTipBox';
import { determineColorPalette, EXPLICIT_MOD_TEXT_COLOR } from './ToolTipColorUtil';

const TOOL_TIP_RIGHT_PADDING = 10;

const getBoundingRectangleOfTargetElement = (event) => {
    let relatedElement = event.target;
    if (relatedElement.childNodes.length === 0) {
        relatedElement = relatedElement.parentNode;
    }
    return relatedElement.getBoundingClientRect();
}

const Tooltip = ({ component, item }) => {

    // TODO: change the logic for displaying the tooltips, 
    // have it as a hidden element and set it to visible on hover 
    // mouse enter instead of having it as a state.
    // It makes a new "make-styles" block each rerender of the same tooltip 
    const [isShown, setIsShown] = useState(false);
    const [yPos, setYPos] = useState(null);
    const [xPos, setXPos] = useState(null);
    const [colorPalette, setColorPalette] = useState([]);

    const handleOnMouseEnter = (event) => {

        //Getting the position of the related element and setting the X and Y coors for the tooltip
        const boundingClientRectangle = getBoundingRectangleOfTargetElement(event);
        setYPos(boundingClientRectangle.top);
        setXPos(boundingClientRectangle.right + TOOL_TIP_RIGHT_PADDING);

        setIsShown(true);
    }

    const handleOnMouseLeave = () => {
        setIsShown(false);
        setYPos(null);
        setXPos(null);
    }

    useEffect(() => {
        setColorPalette(determineColorPalette(item));
    }, [])

    return (
        <>
            <div className='toolTipImage'
                style={
                    {
                        backgroundColor: colorPalette[2],
                        padding: '5px',
                        margin: '5px',
                        border: `1px solid ${colorPalette[0]}`,
                    }
                }
                onMouseEnter={(e) => handleOnMouseEnter(e)}
                onMouseLeave={handleOnMouseLeave}
            >
                {component}
            </div>
            { (isShown) ?
                <ToolTipBox
                    item={item}
                    yPos={yPos}
                    xPos={xPos}
                    primaryColor={colorPalette[0]}
                    secondaryColor={colorPalette[1]}
                    explicitModText={EXPLICIT_MOD_TEXT_COLOR} />
                : null}
        </>
    )
}

Tooltip.propTypes = {
    component: PropTypes.element,
    item: PropTypes.object,
}

export default Tooltip;