package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EtudiantServiceImplMock {

    @Mock
    private EtudiantRepository etudiantRepository;  // Mocked dependency

    @InjectMocks
    private EtudiantServiceImpl etudiantService;    // Class under test

    private Etudiant etudiant;

    @BeforeEach
    public void setUp() {
        // Initialize an Etudiant object before each test
        etudiant = new Etudiant(1L, "John", "Doe", 123456789, null);
    }

    @Test
    public void testAddEtudiant() {
        // Arrange: Define the behavior of the mocked repository
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        // Act: Call the method under test
        Etudiant result = etudiantService.addEtudiant(etudiant);

        // Assert: Verify the result and interactions
        assertNotNull(result);
        assertEquals("John", result.getNom());
        assertEquals("Doe", result.getPrenom());
        verify(etudiantRepository, times(1)).save(etudiant); // Verify save was called once
    }

    @Test
    public void testGetEtudiantById() {
        // Arrange: Define the behavior of the mocked repository
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));

        // Act: Call the method under test
        Etudiant result = etudiantService.retrieveEtudiant(1L);

        // Assert: Verify the result and interactions
        assertNotNull(result);
        assertEquals("John", result.getNom());
        verify(etudiantRepository, times(1)).findById(1L); // Verify findById was called once
    }

    @Test
    public void testRetrieveAllEtudiants() {
        // Arrange: Prepare a list of Etudiants
        List<Etudiant> etudiants = Arrays.asList(
                etudiant,
                new Etudiant(2L, "Jane", "Doe", 987654321, null)
        );
        when(etudiantRepository.findAll()).thenReturn(etudiants);

        // Act: Call the method under test
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        // Assert: Verify the result and interactions
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(etudiantRepository, times(1)).findAll(); // Verify findAll was called once
    }

    @Test
    public void testModifyEtudiant() {
        // Arrange: Define the behavior of the mocked repository
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        // Act: Call the method under test
        Etudiant result = etudiantService.modifyEtudiant(etudiant);

        // Assert: Verify the result and interactions
        assertNotNull(result);
        assertEquals("John", result.getNom());
        verify(etudiantRepository, times(1)).save(etudiant); // Verify save was called once
    }

    @Test
    public void testRemoveEtudiant() {
        // Act: Call the method under test
        etudiantService.removeEtudiant(1L);

        // Assert: Verify the interaction with the mocked repository
        verify(etudiantRepository, times(1)).deleteById(1L); // Verify deleteById was called once
    }

    @Test
    public void testRecupererEtudiantParCin() {
        // Arrange: Define the behavior of the mocked repository
        when(etudiantRepository.findEtudiantByCinEtudiant(123456789)).thenReturn(etudiant);

        // Act: Call the method under test
        Etudiant result = etudiantService.recupererEtudiantParCin(123456789);

        // Assert: Verify the result and interactions
        assertNotNull(result);
        assertEquals("John", result.getNom());
        verify(etudiantRepository, times(1)).findEtudiantByCinEtudiant(123456789); // Verify findEtudiantByCin was called once
    }
}
