import React from 'react';
import PropTypes from 'prop-types';

const SingleItemProperty = ({ property, classes, }) => {


    return (
        <>
            {
                (property)
                    ?
                    (
                        <>
                            <section className='enchantedMod'>

                                <span>{property}</span> : null

                            </section>
                            <div className={classes.breakText} />
                        </>
                    )
                    : null
            }

        </>
    )
}

SingleItemProperty.propTypes = {
    property: PropTypes.string,
    classes: PropTypes.object,
}

export default SingleItemProperty;