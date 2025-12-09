# Changelog: Lab 12 - JavaScript & jQuery

**Task ID:** lab12-javascript-jquery  
**Status:** ✅ Complete  
**Date:** 2025-12-09

## Files Updated

| File | Action |
|------|--------|
| `CS3220/lab12/add.html` | CREATED |
| `CS3220/lab12/conversion.html` | UPDATED |
| `CS3220/lab12/assets/javascripts/add.js` | CREATED |
| `CS3220/lab12/assets/javascripts/conversion.js` | CREATED |
| `CS3220/index.html` | UPDATED |

## Summary

Implemented Lab 12 which practices JavaScript and jQuery for DOM manipulation and event handling. Created two interactive pages:

1. **add.html** - Two input fields with an "Add" button that calculates and displays the sum
2. **conversion.html** - Unit conversion from inches to cm/inch/feet/meter/yard with live updates

## Implementation Details

### add.html + add.js
- Two text inputs for numbers
- Button click triggers addition
- Result displayed in a `<span>` element
- Uses jQuery for event handling and DOM updates

### conversion.html + conversion.js
- Single text input (interpreted as inches)
- Dropdown with 5 unit options: cm, inch, feet, meter, yard
- Live conversion on both input change and dropdown change
- Conversion factors:
  - cm: 2.54
  - inch: 1
  - feet: 1/12
  - meter: 0.0254
  - yard: 1/36

## Folder Structure

```
lab12/
├── add.html
├── conversion.html
└── assets/
    └── javascripts/
        ├── add.js
        └── conversion.js
```

## Issues Encountered

None.

## Future Work

None required.
