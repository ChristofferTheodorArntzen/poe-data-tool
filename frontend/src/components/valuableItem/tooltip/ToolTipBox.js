/* eslint-disable no-unused-vars */
import React, { useEffect } from 'react';
import PropTypes from 'prop-types';
import ItemProperties from './ItemProperties';
import ItemRequirements from './ItemRequirements';
import SingleItemProperty from './SingleItemProperty';
import ItemExplicitMods from './ItemExplicitMods';

import { makeStyles } from '@material-ui/core';

const useStyles = makeStyles({
    itemBox: {
        fontSize: '10px',
        position: 'absolute',
        display: 'inline-block',
        padding: '2px',
        top: props => props.yPos,
        left: props => props.xPos,
        backgroundColor: 'rgba( 0, 0, 0, 0.7)',
        border: `3px solid`,
        borderColor: props => props.primaryColor,
    },

    hidden: {
        visibility: 'hidden',
    },
    itemBoxHeader: {
        backgroundColor: props => props.primaryColor,
    },
    itemName: {

    },
    typeLine: {

    },
    breakText: {
        backgroundImage: 'linear-gradient(to right,transparent,hsla(var(--item-unique),var(--opacity-50)),transparent)',
        padding: '0',
        height: '8px',
    },
    requirements: {
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'center',
    },
    properties: {
    },
    attributeValue: {
        color: 'white',
        paddingLeft: '5px'
    },
    attributeName: {
        color: '#9e9e9e',
        paddingLeft: '5px'
    }

});

const ToolTipBox = (props) => {

    const { item, primaryColor } = props;
    const {
        name,
        typeLine,
        id,
        properties,
        requirements,
        implicitMod,
        enchantedMod,
        explicitMods,
        flavourText,
        corrupted,
    } = item;

    const classes = useStyles(props);

    return (
        <div className={`${classes.itemBox}`} id={`itemBox-${id}`}>
            <article>
                <section className={classes.itemBoxHeader}>
                    <div className={classes.itemName}>
                        <span>
                            {(name) ? name : typeLine}
                        </span>
                    </div>
                    <div className={classes.typeLine}>
                        <span>
                            {typeLine}
                        </span>
                    </div>
                </section>
                <div className={classes.breakText} />



                <div className='itemDescription'>
                    <ItemProperties properties={properties} classes={classes} />
                    <ItemRequirements requirements={requirements} classes={classes} />
                    {(enchantedMod) ? <SingleItemProperty property={enchantedMod} classes={classes} /> : null}
                    {(implicitMod) ? <SingleItemProperty property={implicitMod} classes={classes} /> : null}
                    <ItemExplicitMods  explicitMods={explicitMods} classes={classes} />
                    <section className='descriptionText'>
                        {
                            (flavourText)
                                ?
                                flavourText.map((text) => (
                                    <div key={text}>
                                        <span>{text}</span>
                                    </div>
                                ))
                                : null
                        }
                    </section>
                </div>
            </article>
        </div >
    )
}

ToolTipBox.propTypes = {
    item: PropTypes.object.isRequired,
    yPos: PropTypes.number,
    xPos: PropTypes.number,
    primaryColor: PropTypes.string,
}

export default ToolTipBox;