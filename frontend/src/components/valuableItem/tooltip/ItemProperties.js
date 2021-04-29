import React from 'react'
import PropTypes from 'prop-types'

const ItemProperties = ({ properties, classes }) => {

    return (
        <>
            {
                (properties)
                    ?
                    (
                        <>
                            <section className={classes.properties}>
                                {

                                    (properties)
                                        ? (
                                            properties.map((property) => (
                                                <div key={`properties-${property.name}`}>
                                                    <span className={classes.attributeName}>
                                                        {`${property.name} 
                                                    ${(property.values.length > 0)
                                                                ? ':'
                                                                : ''}`}
                                                    </span>
                                                    <span className={classes.attributeValue}>
                                                        {(property.values.length > 0)
                                                            ? `${parseFloat(property.values[0])}`
                                                            : null}
                                                    </span>
                                                </div>
                                            ))
                                        )
                                        : null
                                }
                            </section>
                            <div className={classes.breakText} />
                        </>)
                    : null
            }

        </>
    )
}

ItemProperties.propTypes = {
    properties: PropTypes.array,
    classes: PropTypes.object,
}

export default ItemProperties;
