import React from 'react';
import PropTypes from 'prop-types';

const ItemImplicitMods = ({ implicitMods, classes }) => {

    return (
        <>
            {
                (implicitMods)
                    ?

                    (
                        <>
                        <section className={classes.implicitMods} style={{ paddingLeft: '5px', paddingRight: '5px' }}>
                            {implicitMods.map((implicitMod) => (
                                <div key={implicitMod.name} className={classes.explicitMods}>
                                    <span>{implicitMod}</span>
                                </div>   
                            ))}
                        </section>
                        <div className={classes.breakText} />
                        </>
                    )

                    : null
            }
        </>
    )


}

ItemImplicitMods.propTypes = {
    implicitMods: PropTypes.array,
    classes: PropTypes.object,
}

export default ItemImplicitMods;





{/* <>
<section className={classes.implicitMods} style={{ paddingLeft: '10px', paddingRight: '10px' }}>
    {
        (implicitMods) ? implicitMods.map((implicitMod) => (
            <div key={`requirements-${implicitMod}`}>
                <span>{implicitMod}</span>
            </div>
        )
        ) : null
    }

</section>
<div className={classes.breakText} />
</> */}