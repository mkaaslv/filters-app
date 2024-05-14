import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFilterButtonComponent } from './add-filter-button.component';

describe('AddFilterButtonComponent', () => {
  let component: AddFilterButtonComponent;
  let fixture: ComponentFixture<AddFilterButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddFilterButtonComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddFilterButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
