import React from 'react'
import PropTypes from 'prop-types'

const ItemExplicitMods = ({explicitMods, corrupted, classes}) => {


    return (
        <>
            <section className='explicitMods'>
                {
                    (explicitMods) ? explicitMods.map((explicitMod) => (
                        <div key={`requirements-${explicitMod}`}>
                            <span>{explicitMod}</span>
                        </div>
                    )
                    ) : null
                }
                <span>{(corrupted) ? 'Corrupted' : null}</span>
            </section>
            <div className={classes.breakText} />
        </>
    )

}

ItemExplicitMods.propTypes = {
    explicitMods: PropTypes.array,
    corrupted: PropTypes.bool,
    classes: PropTypes.object,

}

export default ItemExplicitMods;