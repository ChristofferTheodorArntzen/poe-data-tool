import React from 'react';
import PropTypes from 'prop-types';
import ItemProperties from './ItemProperties';
import ItemRequirements from './ItemRequirements';
import SingleItemProperty from './SingleItemProperty';
import ItemExplicitMods from './ItemExplicitMods';
import ItemDescriptionText from './ItemDescriptionText';
import ItemImplicitMods from './ItemImplicitMods';

import { makeStyles } from '@material-ui/core';

//TODO: this needs to be moved somewhere else, this adds a style block to header each time a tooltip is rendered.
const useStyles = makeStyles({
    itemBox: {
        fontSize: '12px',
        position: 'absolute',
        display: 'inline-block',
        padding: '2px',
        top: props => props.yPos,
        left: props => props.xPos,
        backgroundColor: 'rgba( 0, 0, 0, 0.8)',
        border: `3px solid`,
        borderColor: props => props.primaryColor,
    },
    itemBoxHeader: {
        backgroundColor: props => props.primaryColor,
        color: props => props.secondaryColor,
        fontWeight: 750,
        marginBottom: '4px',
    },
    breakText: {
        height: '1px',
        backgroundImage: props => `linear-gradient(90deg, rgba(0,0,0,0) 0%, ${props.primaryColor} 50%, rgba(0,0,0,0) 100%)`,
        margin: '4px',
    },
    requirements: {
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'center',
    },
    attributeValue: {
        color: 'white',
        paddingLeft: '5px'
    },
    attributeName: {
        color: '#9e9e9e',
        paddingLeft: '5px'
    }, 
    explicitMods: {
        color: props => props.explicitModText
    },
    descriptionText: {
        color: props => props.primaryColor
    },
    visible: {
        visibility: 'visible',
        top: props => props.yPos,
        left: props => props.xPos,
    }

});

const ToolTipBox = (props) => {

    const { item, } = props;
    const {
        name,
        typeLine,
        id,
        properties,
        requirements,
        implicitMods,
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
                <div className='itemDescription'>
                    <ItemProperties properties={properties} classes={classes} />
                    <ItemRequirements requirements={requirements} classes={classes} />
                    <ItemImplicitMods implicitMods={implicitMods} classes={classes} />
                    {(enchantedMod) ? <SingleItemProperty property={enchantedMod} classes={classes} /> : null}

                    <ItemExplicitMods  explicitMods={explicitMods} corrupted={corrupted} classes={classes} />
                    <ItemDescriptionText flavourText={flavourText} classes={classes} />
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
    secondaryColor: PropTypes.string,
    isShown: PropTypes.bool,
}

export default ToolTipBox;