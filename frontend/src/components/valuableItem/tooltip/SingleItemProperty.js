import React from 'react';
import PropTypes from 'prop-types';

const SingleItemProperty = ({ property, classes, }) => {


    return (
        <>
            <section className='enchantedMod'>
                {
                    (property) ? <span>{property}</span> : null
                }
            </section>
            <div className={classes.breakText} />
        </>
    )
}

SingleItemProperty.propTypes = {
    property: PropTypes.string,
    classes: PropTypes.object,
}

export default SingleItemProperty;