import React from 'react'
import PropTypes from 'prop-types'

const ItemRequirements = ({ requirements, classes }) => {

    return (
        <>
            {
                (requirements)
                    ?
                    (
                        <>
                            <section className={classes.requirements}>
                                <span className={classes.attributeName}>Requirements: </span>
                                {
                                    (requirements) ? requirements.map((requirement) => (
                                        <div key={`properties-${requirement.name}`}>
                                            <span className={classes.attributeName}>
                                                {`${requirement.name}`}
                                            </span>
                                            <span className={classes.attributeValue}>
                                                {`${parseFloat(requirement.values[0])}`}
                                            </span>
                                        </div>
                                    )) : null
                                }
                            </section>
                            <div className={classes.breakText} />
                        </>)
                    : null
            }

        </>
    )
}

ItemRequirements.propTypes = {
    requirements: PropTypes.array,
    classes: PropTypes.object,
}

export default ItemRequirements;
