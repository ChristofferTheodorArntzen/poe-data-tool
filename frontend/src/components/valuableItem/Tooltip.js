/* eslint-disable no-unused-vars */
import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/core';
import ToolTipBox from './ToolTipBox';

const useStyles = makeStyles(() => ({

    itemBox: {
        position: 'relative',
        display: 'inline-block',
        backgroundColor: 'black',
    },
    hidden: {
        visibility: 'hidden',
    }

}));

const Tooltip = ({ component, item }) => {

    const classes = useStyles();
    const [isShown, setIsShown] = useState(false);
    
    return (
        <>
            <div className='toolTipImage'
                onMouseEnter={() => setIsShown(true)}
                onMouseLeave={() => setIsShown(false)}
            >
                {component}
            </div>

            { isShown ? <ToolTipBox shown={isShown} item={item} /> : null }
            
        </>
    )
}

Tooltip.propTypes = {
    component: PropTypes.element,
    item: PropTypes.object,
}

export default Tooltip;