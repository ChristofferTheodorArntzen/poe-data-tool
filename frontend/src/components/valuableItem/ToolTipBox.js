import React from 'react';
import PropTypes from 'prop-types';

import { makeStyles } from '@material-ui/core';

const useStyles = makeStyles(() => ({

    itemBox: {
        position: 'absolute',
        display: 'inline-block',
        backgroundColor: 'black',
    },
    
    hidden: {
        visibility: 'hidden',
    },

}));

const ToolTipBox = ({ item }) => {

    const { name, typeLine } = item;

    const classes = useStyles();

    return (
        <div className={`${classes.itemBox}`} >
            <article>
                <section className='itemBoxHeader'>
                    <div className='itemName'>
                        <span>
                            {name}
                        </span>
                    </div>
                    <div className='typeLine'>
                        <span>
                            {typeLine}
                        </span>
                    </div>
                </section>
                <div className='itemDescription'>
                    <section className='properties'>

                    </section>
                    <br />
                    <section className='requirements'>

                    </section>
                    <br />
                    <section className='explicitMods'>

                    </section>
                    <br />
                    <section className='descriptionText'>

                    </section>
                    <br />
                </div>
            </article>
        </div >
    )
}

ToolTipBox.propTypes = {
    item: PropTypes.object.isRequired,
}

export default ToolTipBox;