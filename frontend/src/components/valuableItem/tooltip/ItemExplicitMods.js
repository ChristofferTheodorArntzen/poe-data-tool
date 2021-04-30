import React from 'react'
import PropTypes from 'prop-types'

const ItemExplicitMods = ({ explicitMods, corrupted, classes }) => {

    return (
        <>
            {
                (explicitMods)
                    ?
                    <>
                        <section className={classes.explicitMods} style={{ paddingLeft: '10px', paddingRight: '10px' }}>
                            {
                                explicitMods.map((explicitMod) => (
                                    <div key={`requirements-${explicitMod}`}>
                                        <span>{explicitMod}</span>
                                    </div>
                                ))
                            }

                            <span style={{ color: 'red' }}>{(corrupted) ? 'Corrupted' : null}</span>
                        </section>
                        <div className={classes.breakText} />
                    </>
                    : null

            }


        </>
    )

}

ItemExplicitMods.propTypes = {
    explicitMods: PropTypes.array,
    corrupted: PropTypes.bool,
    classes: PropTypes.object,

}

export default ItemExplicitMods;