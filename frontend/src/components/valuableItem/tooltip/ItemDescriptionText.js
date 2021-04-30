import React from 'react';
import PropTypes from 'prop-types';

const ItemDescriptionText = ({ flavourText, classes }) => {
    return (
        <section className={classes.descriptionText}>
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
        </section>)

}

ItemDescriptionText.propTypes = {
    flavourText: PropTypes.array,
    classes: PropTypes.object,
}

export default ItemDescriptionText;