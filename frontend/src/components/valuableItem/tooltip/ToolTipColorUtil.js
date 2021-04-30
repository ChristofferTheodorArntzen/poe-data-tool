/* eslint-disable no-unused-vars */

//FrameType variable determine color, list of frameType constants
const MAGIC_ITEM = 1;
const RARE_ITEM = 2;
const UNIQUE_ITEM = 3;
const GEM_ITEM = 4;
const CURRENCY_ITEM = 5;
const CARD_ITEM = 6;
const PROPHECY_ITEM = 8;

const MAGIC_COLOR = 'rgba(43, 149, 255, 0.7)';
const MAGIC_BACKGROUND_COLOR = 'rgba(43, 149, 255, 0.2)';
const MAGIC_HEX_TEXT_COLOR = '#b3d9ff';

const RARE_COLOR = 'rgba(255, 219, 87, 0.7)';
const RARE_BACKGROUND_COLOR = 'rgba(255, 219, 87, 0.2)';
const RARE_HEX_TEXT_COLOR = '#ffeeb3';

const UNIQUE_COLOR = 'rgba(255, 160, 38, 0.7)';
const UNIQUE_BACKGROUND_COLOR = 'rgba(255, 160, 38, 0.2)';
const UNIQUE_HEX_TEXT_COLOR = '#ffdeb3';

const GEM_COLOR = 'rgba(0, 204, 255, 0.7)';
const GEM_BACKGROUND_COLOR = 'rgba(0, 204, 255, 0.2)';
const GEM_TEXT_COLOR = '#b3f0ff';

const PROPHECY_COLOR = 'rgba(188, 1, 188, 0.7)';
const PROPHECY_BACKGROUND_COLOR = 'rgba(188, 1, 188, 0.2)';
const PROPHECY_TEXT_COLOR = '#fe67fe';

const CURRENCY_COLOR = 'rgba(204, 153, 0, 0.7)';
const CURRENCY_BACKGROUND_COLOR = 'rgba(204, 153, 0, 0.2)';
const CURRENCY_TEXT_COLOR = '#ffd966';

const DEFAULT_COLOR = 'rgba(140, 140, 140, 0.7)';
const DEFAULT_BACKGROUND_COLOR = 'rgba(140, 140, 140, 0.2)';
const DEFAULT_TEXT_COLOR = '#cccccc';

export const EXPLICIT_MOD_TEXT_COLOR = '#6795eb';

export const determineColorPalette = (item) => {
    switch (item.frameType) {
        case MAGIC_ITEM: return [MAGIC_COLOR, MAGIC_HEX_TEXT_COLOR, MAGIC_BACKGROUND_COLOR];
        case RARE_ITEM: return [RARE_COLOR, RARE_HEX_TEXT_COLOR, RARE_BACKGROUND_COLOR];
        case UNIQUE_ITEM: return [UNIQUE_COLOR, UNIQUE_HEX_TEXT_COLOR, UNIQUE_BACKGROUND_COLOR];
        case GEM_ITEM: return [GEM_COLOR, GEM_TEXT_COLOR, GEM_BACKGROUND_COLOR];
        case PROPHECY_ITEM: return [PROPHECY_COLOR, PROPHECY_TEXT_COLOR, PROPHECY_BACKGROUND_COLOR];
        case CURRENCY_ITEM: return [CURRENCY_COLOR, CURRENCY_TEXT_COLOR, CURRENCY_BACKGROUND_COLOR];
        default: return [DEFAULT_COLOR, DEFAULT_TEXT_COLOR, DEFAULT_BACKGROUND_COLOR];
    }
}